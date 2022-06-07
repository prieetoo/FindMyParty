import React, { useState, useEffect } from "react";
import { Dimensions, StyleSheet, Text, View } from "react-native";
import * as Location from "expo-location";
import MapView, { PROVIDER_GOOGLE }  from "react-native-maps";
import LottieView from "lottie-react-native";
import locationData from "../data/locationData.json"
import fetchedEvents from "../data/fetchedEvents.json"
import searchFilters from "../data/searchFilters.json"

export const MyMapComponent = () => {

  const [location, setLocation] = useState(null);
  const [errorMsg, setErrorMsg] = useState(null);

  // Request access for location permissions and store them
  useEffect(() => {
    (async () => {
      let { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== "granted") {
        setErrorMsg("Permission to access location was denied");
        return;
      }

      let location = await Location.getCurrentPositionAsync({});
      setLocation(location);
      if (location.coords.latitude != null) {locationData.location.latitude = location.coords.latitude;}
      if (location.coords.longitude != null) {locationData.location.longitude = location.coords.longitude;}
      fetchEvents;

    })();
  }, []);

  return (
    <View>
      {location ? (
        <MapView
          // Initialize the map around the user's locations
          provider={PROVIDER_GOOGLE}
          showsUserLocation={true}
          initialRegion={{
            latitude: location.coords.latitude,
            longitude: location.coords.longitude,
            latitudeDelta: 0.008,
            longitudeDelta: 0.008,
          }}
          onRegionChangeComplete = { fetchEvents }
          style={styles.map} >
          
          { fetchedEvents.events.lista_eventos ? (
            (fetchedEvents.events.lista_eventos.map((event) => {
            return(
            <MapView.Marker
            // Place a map marker at the user's location
            key = { event.id }
            coordinate = {{
            latitude: Number(event.latitud),   
            longitude: Number(event.longitud),
            }}
            title = { event.nombre }
            description = { String(event.descripcion)}
            />
            )
          }))) : ("Hello")
        }
          
          {/*
            <Marker
            // Place a map marker at the user's location
            coordinate={{
              latitude: location.coords.latitude,
              longitude: location.coords.longitude,
            }}
            pinColor = {"blue"}
            title = "Current location"
          />
          */}

        </MapView>

      ) : (
        <View style = {styles.container}>
            {/*<Text> {errorMsg || "Gathering location..."} </Text>*/}
            <LottieView
                source={require("../assets/loading/107547-loading-grey.json")}
                style={styles.animation}
                autoPlay
            />
        </View>
      )}
    </View>
  );
};

const fetchEvents = () => {

  try { 
    let response = fetch('http://192.168.68.107:8080/event/get_events', {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          latitud: locationData.location.latitude,
          longitud: locationData.location.longitude,
          etiquetas: searchFilters.tag,
          radio: searchFilters.distance,
          pago: searchFilters.isPaid,
          participantes: searchFilters.assistants,
          dia: searchFilters.dayOfTheWeek
        })
    })
    .then(response => response.json())
    .then(data => {
      fetchedEvents.events = data;
      console.log(fetchedEvents.events)
    })
}
  catch (error) {
    console.error(error);
  }
}


const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    justifyContent: 'center',
    width: Dimensions.get("window").width, 
    height: Dimensions.get("window").height
  },
  map: {
    width: Dimensions.get("window").width,
    height: Dimensions.get("window").height,
  },
  
  animation: {
    width: 150,
    height: 150,
  },
});