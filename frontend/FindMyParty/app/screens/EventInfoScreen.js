import React from 'react';
import { Alert, Dimensions, ImageBackground, Pressable, ScrollView, StatusBar, StyleSheet, Text, View } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import { useNavigation } from '@react-navigation/native';
import user from '../data/user.json';
import * as Linking from 'expo-linking';

export default function EventInfoScreen(props){

    const image = {uri: "https://direct.rhapsody.com/imageserver/images/alb.315707992/500x500.jpg"};
    const navigation = useNavigation();
    const id = props.route.params.id;
    const nombre = props.route.params.nombre;
    const desc = props.route.params.desc;
    const lat = props.route.params.lat;
    const long = props.route.params.lon;
    const part = props.route.params.part;
    const reviews = props.route.params.com;
    const dist = props.route.params.dist;
    const autor = props.route.params.autor;

    return (
    <View
      showsVerticalScrollIndicator={false}
      contentContainerStyle={{
        height: 200,
        backgroundColor: colors.white,
        paddingBottom: 20,
      }}>
          <StatusBar
        barStyle="light-content" 
        translucent
        backgroundColor="rgb(255, 255, 255)"
      />
      <ImageBackground style={style.headerImage} source={image}>
        <View style={style.header}>
          <Icon
            name="arrow-back-ios"
            size={28}
            color={colors.white}
            onPress = {() => navigation.goBack()}
          />
          { autor == user.id ? (
          <Icon 
            name="delete" 
            size={28} color={colors.white}
            onPress = {() => deleteEvent(id, navigation)}
            />) : (null)
          }
        </View>
      </ImageBackground>
      
      <View>
        <View style={style.iconContainer}>
          <Icon name="place" color={colors.white} size={28} onPress = {() => Linking.openURL("https://www.google.com/maps/search/?api=1&query=" + Number(lat) + "," + Number(long))}/>
        </View>
        <View style={{marginTop: 25, paddingHorizontal: 20}}>
          <Text style={{ fontSize: 30, fontWeight: 'bold'}}> { nombre } </Text>
          <View
            style={{
              marginTop: 10,
              flexDirection: 'row',
              justifyContent: 'space-between',
            }}>
            <View style={{flexDirection: 'row'}}>
              <View style={{flexDirection: 'row'}}>
              <Text style={{fontWeight: 'bold', fontSize: 20, alignSelf: 'center', color: 'rgb(100, 100, 100)'}}> {dist} km away</Text>
              </View>
            </View>
            <Text style={{fontSize: 13, color: colors.grey, alignSelf: 'center'}}> { reviews } reviews</Text>
          </View>
          <ScrollView showsVerticalScrollIndicator={true} style={{marginTop: 20, maxHeight: 200}}>
            <Text style={{lineHeight: 20, color: colors.grey}}>
            {desc}
            </Text>
          </ScrollView>
        </View>
        <View
          style={{
            marginTop: 20,
            flexDirection: 'row',
            justifyContent: 'space-between',
            paddingLeft: 20,
            alignItems: 'center',
          }}>
               <Text style={{fontSize: 20, fontWeight: 'bold'}}>
            Participants
          </Text>
          <View style={style.priceTag}>
            <Text
              style={{
                fontSize: 12,
                fontWeight: 'bold',
                color: colors.grey,
                marginLeft: 5,
              }}>
               { part } users
            </Text>
          </View>
        </View>
        <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, style.btn]} onPress = {() => { joinEvent(id, navigation) }}>
          <Text style={{color: colors.white, fontSize: 18, fontWeight: 'bold'}}>
            Join Now
          </Text>
        </Pressable>
      </View>
    </View>
)};

const deleteEvent = (id, navigation) => {
  try { 
    let response = fetch('http://192.168.68.107:8080/event/eliminate/' + id, {
        method: 'GET',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        },
    })
    .then(response => response.json())
    .then(data => {
      
      var result = parseInt(data.result)

      if (result == 1) {
        Alert.alert("Deleted", "This event has been deleted")
        navigation.goBack();
      }
      else {
        Alert.alert("Error", "There has been an error deleting the event. Please, try again."); 
      }
    }
  )
}
  catch (error) {
    console.error(error);
  }
}

const joinEvent = (id, navigation) => {
  try { 
    let response = fetch('http://192.168.68.107:8080/event/join/' + id + "/" + user.id, {
        method: 'GET',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        },
    })
    .then(response => response.json())
    .then(data => {
      
      var result = parseInt(data.result)

      if (result == 1) {
        Alert.alert("Joined", "You joined this event")
        navigation.goBack();
      }
      else {
        Alert.alert("Error", "There has been an error joining the event. Please, try again."); 
      }
    }
  )
}
  catch (error) {
    console.error(error);
  }
}


const style = StyleSheet.create({

  container: {
    paddingBottom: 500,
    alignItems: 'center',
    justifyContent: 'center',
    alignSelf: 'center',
    width: Dimensions.get("window").width, 
    height: Dimensions.get("window").height
  },

  btn: {
    height: 55,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 200,
    backgroundColor: colors.primary,
    marginHorizontal: 20,
    marginBottom: 20,
    borderRadius: 10,
  },

  priceTag: {
    height: 40,
    alignItems: 'center',
    marginLeft: 40,
    paddingLeft: 20,
    flex: 1,
    backgroundColor: colors.secondary,
    borderTopLeftRadius: 20,
    borderBottomLeftRadius: 20,
    flexDirection: 'row',
  },
  iconContainer: {
    position: 'absolute',
    height: 60,
    width: 60,
    backgroundColor: colors.primary,
    top: -30,
    right: 20,
    borderRadius: 30,
    justifyContent: 'center',
    alignItems: 'center',
  },
  headerImage: {
    height: 400,
    borderBottomRightRadius: 40,
    borderBottomLeftRadius: 40,
    overflow: 'hidden',
  },
  header: {
    marginTop: 60,
    flexDirection: 'row',
    alignItems: 'center',
    marginHorizontal: 20,
    justifyContent: 'space-between',
  },

  animation: {
    width: 150,
    height: 150,
    alignItems: 'center'
  },
});
