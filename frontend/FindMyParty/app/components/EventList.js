import React from 'react'
import { Text, Image, View, Pressable, ScrollView, SafeAreaView } from 'react-native'
import { listEvents } from '../styles/styles';
import { useNavigation } from '@react-navigation/native';
import fetchedEvents from '../data/fetchedEvents.json';

export function EventList() {

    const navigation = useNavigation(); 

    return (
    <SafeAreaView>
        <View style = {listEvents.titleBox}>
            <Text style = {listEvents.title}> Events near you </Text>
        </View>

        <View style = {listEvents.actionsBox}>
            <Text style = {listEvents.listCreateEvent} onPress = {() => navigation.navigate('CreateEvent')}> Create event </Text>
            <Text style = {listEvents.listFilters} onPress = {() => navigation.navigate('Filters')}> Filters... </Text>
        </View>
        
        <ScrollView style = {listEvents.eventScroll}>
        
        {fetchedEvents.events.lista_eventos ? (
            (fetchedEvents.events.lista_eventos.map((event) => {
                var dist = String(event.distancia).slice(0,4)
                return(
                        <Pressable style = {listEvents.eventElement} key = { event.id } onPress = {() => navigation.navigate("EventInfo", {id: event.id})}>
                            <View style = {listEvents.eventDetails}>
                                <Text style = {listEvents.eventTitle}> {event.nombre} </Text>
                                <Text style = {listEvents.eventDistance}> {dist} km away </Text>
                            </View>
                            <Image source={require('../assets/arrow.png')} style = {listEvents.eventNavigate}/>
                        </Pressable>
        )}))) : (<Text> No hay eventos disponibles. </Text>)
        }
        
        </ScrollView> 
        
        <Text style = {{alignSelf: 'center', paddingTop: 20, fontSize: 20, color: 'rgb(62, 167, 253)'}} onPress = {() => navigation.navigate('Settings')}> Settings </Text>
    </SafeAreaView>
    )
}