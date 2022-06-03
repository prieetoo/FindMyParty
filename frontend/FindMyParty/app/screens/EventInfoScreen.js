import React, { useEffect, useState } from 'react';
import { Dimensions, ImageBackground, ScrollView, StatusBar, StyleSheet, Text, View } from 'react-native';
import colors from '../styles/colors';
import Icon from 'react-native-vector-icons/MaterialIcons';
import { useNavigation } from '@react-navigation/native';
import eventDetails from '../data/eventDetails.json';
import LottieView from "lottie-react-native";

export default function EventInfoScreen(props){

    const image = {uri: "https://direct.rhapsody.com/imageserver/images/alb.315707992/500x500.jpg"};
    const navigation = useNavigation();
    const id = props.route.params.id;
    var [loaded, isLoaded] = useState(false); 

    useEffect (() => {
      fetchInfo(id, loaded);
    }, [])

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
          <Icon 
            name="border-color" 
            size={28} color={colors.white}
            onPress = {() => navigation.navigate("EditEvent")}
            />
        </View>
      </ImageBackground>
      
      { loaded = true ? (
      <View>
        <View style={style.iconContainer}>
          <Icon name="place" color={colors.white} size={28} />
        </View>
        <View style={{marginTop: 25, paddingHorizontal: 20}}>
          <Text style={{ fontSize: 30, fontWeight: 'bold'}}> { eventDetails.details.nombre } </Text>
          <View
            style={{
              marginTop: 10,
              flexDirection: 'row',
              justifyContent: 'space-between',
            }}>
            <View style={{flexDirection: 'row'}}>
              <View style={{flexDirection: 'row'}}>
                <Icon name="star" size={30} color={colors.orange} />
                <Icon name="star" size={30} color={colors.orange} />
                <Icon name="star" size={30} color={colors.orange} />
                <Icon name="star" size={30} color={colors.orange} />
                <Icon name="star" size={30} color={colors.grey} />
              </View>
              <Text style={{fontWeight: 'bold', fontSize: 23, marginLeft: 5, alignSelf: 'center', marginTop: 2,}}>
                4.0
              </Text>
            </View>
            <Text style={{fontSize: 13, color: colors.grey, alignSelf: 'center'}}> { String(eventDetails.details.comentarios.length) } reviews</Text>
          </View>
          <ScrollView showsVerticalScrollIndicator={true} style={{marginTop: 20, maxHeight: 200}}>
            <Text style={{lineHeight: 20, color: colors.grey}}>
            A party in my private pool! 😎
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
              { String(eventDetails.details.participantes.length) } users
            </Text>
          </View>
        </View>
        <View style={style.btn}>
          <Text style={{color: colors.white, fontSize: 18, fontWeight: 'bold'}}>
            Join Now
          </Text>
        </View>
      </View> ) : (
        <View style = {style.container}>
          <LottieView
              source={require("../assets/loading/107547-loading-grey.json")}
              style={style.animation}
              autoPlay
          />
        </View>
      )
}
    </View>
  )
};

const fetchInfo = (id, loaded) => {

  try { 
    let response = fetch('http://192.168.68.107:8080/event/get/' + id, {
        method: 'GET',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        },
    })
    .then(response => response.json())
    .then(data => {
      //console.log(data);
      eventDetails.details = data;
      console.log(eventDetails.details);
      loaded = true;
    })
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
    marginTop: 40,
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
