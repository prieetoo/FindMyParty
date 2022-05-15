import React from 'react'
import { Text, Image, View, Pressable, ScrollView, SafeAreaView } from 'react-native'
import { listEvents } from '../styles/styles';
import { Dropdown } from './FilterPicker';
import { useNavigation } from '@react-navigation/native';

export function EventList(props) {

    const navigation = useNavigation(); 

    return (
    <SafeAreaView>
        <View style = {listEvents.titleBox}>
            <Text style = {listEvents.title}> Events near you </Text>
        </View>

        <View style = {listEvents.actionsBox}>
            <Text style = {listEvents.listActions} onPress = {() => navigation.navigate('CreateEvent')}> Create event </Text>
            <View style = {listEvents.filterDropdownView}>
                <Dropdown/>
            </View>
        </View>


        <ScrollView style = {listEvents.eventScroll}>
            <Pressable style = {listEvents.firstEventElement} onPress = {() => navigation.navigate('EventInfo')}>
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
            <Pressable style = {listEvents.eventElement}>
                <Image source={require('../assets/rave.jpeg')} style = {listEvents.eventPic}/> 
                <View style = {listEvents.eventDetails}>
                    <Text style = {listEvents.eventTitle}> Reggaeton party </Text>
                    <Text style = {listEvents.eventDistance}> 2.5 km away </Text>
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
            <Pressable style = {listEvents.eventElement}>
                <Image source={require('../assets/rave.jpeg')} style = {listEvents.eventPic}/> 
                <View style = {listEvents.eventDetails}>
                    <Text style = {listEvents.eventTitle}> Reggaeton party </Text>
                    <Text style = {listEvents.eventDistance}> 2.5 km away </Text>
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
            <Pressable style = {listEvents.eventElement}>
                <Image source={require('../assets/rave.jpeg')} style = {listEvents.eventPic}/> 
                <View style = {listEvents.eventDetails}>
                    <Text style = {listEvents.eventTitle}> Reggaeton party </Text>
                    <Text style = {listEvents.eventDistance}> 2.5 km away </Text>
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
            <Pressable style = {listEvents.eventElement}>
                <Image source={require('../assets/rave.jpeg')} style = {listEvents.eventPic}/> 
                <View style = {listEvents.eventDetails}>
                    <Text style = {listEvents.eventTitle}> Reggaeton party </Text>
                    <Text style = {listEvents.eventDistance}> 2.5 km away </Text>
                </View>
                <Image source={require('../assets/arrow.png')} style = {listEvents.eventNavigate}/>
            </Pressable>
        </ScrollView>

        <Text style = {{alignSelf: 'center', paddingTop: 20, fontSize: 20, color: 'rgb(62, 167, 253)'}} onPress = {() => navigation.navigate('Settings')}> Settings </Text>
    </SafeAreaView>
    )
}