import React, {useState, useEffect} from 'react';
import { StyleSheet, View, Dimensions } from 'react-native';
import MapView, { PROVIDER_GOOGLE } from 'react-native-maps';
import { mapStyle } from '../styles/mapStyle';
import { useNavigation } from '@react-navigation/native';
import locationData from '../data/locationData.json'
import searchFilters from '../data/searchFilters.json'
import {fetchedEvents } from '../data/fetchedEvents.json'
import { Marker } from 'react-native-maps';
import { render } from 'react-dom';

export function MapScreen() { 

  const navigation = useNavigation();

  return (
    <View>
      <MapView
        customMapStyle={mapStyle}
        provider={PROVIDER_GOOGLE}
        style={styles.mapStyle}
        onMapReady = {fetchEvents}
        onRegionChange = {fetchEvents}
        region={{
          latitude: locationData.location.latitude,
          longitude: locationData.location.longitude,
          latitudeDelta: 0.010,
          longitudeDelta: 0.010,
        }}
        mapType="standard">

          {fetchEvents.map}    
      
      </MapView>
    </View>
  );
}

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
  },
  mapStyle: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height,
  },
});