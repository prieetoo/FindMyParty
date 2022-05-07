import React from 'react'
import { Text, Image, View, SafeAreaView, Pressable, TextInput, StatusBar } from 'react-native'
import { listEvents } from '../styles/styles'

export function EventList() {

    return (
    <View>
        <View style = {listEvents.titleBox}>
            <Text style = {listEvents.title}> Events near you </Text>
        </View>

        <View style = {{paddingTop: 20}}>
            <Pressable style = {listEvents.eventElement}>
                <Image source={require('../assets/rave.jpeg')} style = {listEvents.eventPic}/> 
                <View style = {listEvents.eventDetails}>
                    <Text style = {listEvents.eventTitle}> Rave Cave </Text>
                    <Text style = {listEvents.eventDistance}> 2km away </Text>
                </View>
                <Image source={require('../assets/arrow.png')} style = {listEvents.eventNavigate}/>
            </Pressable>
        </View>
    </View>
    )
}