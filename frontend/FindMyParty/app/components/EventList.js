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
                <Text style = {{padding: 25}}> Example </Text>
            </Pressable>
        </View>
    </View>
    )
}