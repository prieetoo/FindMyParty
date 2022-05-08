import React from 'react'
import { Text, Image, View, Pressable, Button } from 'react-native'
import { listEvents } from '../styles/styles'

export function EventList(props) {

    return (
    <View>
        <View style = {listEvents.titleBox}>
            <Text style = {listEvents.title}> Events near you </Text>
        </View>

        <View style = {listEvents.actionsBox}>
            <Text style = {listEvents.listActions} onPress = {(props) => goToScreen('MapList')}> xCreate event </Text>
            <Text style = {listEvents.listActions}> Filters </Text>
        </View>

        <View>
            <Pressable style = {listEvents.firstEventElement}>
                <Image source={require('../assets/rave.jpeg')} style = {listEvents.eventPic}/> 
                <View style = {listEvents.eventDetails}>
                    <Text style = {listEvents.eventTitle}> Rave Cave </Text>
                    <Text style = {listEvents.eventDistance}> 2 km away </Text>
                </View>
                <Image source={require('../assets/arrow.png')} style = {listEvents.eventNavigate}/>
            </Pressable>
            <Pressable style = {listEvents.eventElement}>
                <Image source={require('../assets/rave.jpeg')} style = {listEvents.eventPic}/> 
                <View style = {listEvents.eventDetails}>
                    <Text style = {listEvents.eventTitle}> Reggaeton party </Text>
                    <Text style = {listEvents.eventDistance}> 2.5 km away </Text>
                </View>
                <Image source={require('../assets/arrow.png')} style = {listEvents.eventNavigate}/>
            </Pressable>
        </View>
    </View>
    )
}