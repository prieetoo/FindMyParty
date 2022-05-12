import React, {useCallback, useRef, useState} from 'react'
import { Text, Image, View, SafeAreaView, Pressable, TextInput, StatusBar, StyleSheet } from 'react-native'
import { GestureHandlerRootView } from 'react-native-gesture-handler'
import BottomSheet, { BottomSheetView } from '@gorhom/bottom-sheet'
import { MapScreen } from '../components/Map'
import { EventList } from '../components/EventList'
import { BlurView } from 'expo-blur'
import { listEvents } from '../styles/styles'
import { Fabutton } from '../components/Fabutton'

export default function MapListScreen(props){

    const sheetRef = useRef(null);
    const snapPoints = ["16%", "32%", "85%", "100%"];

    return(
        <GestureHandlerRootView style = {{ flex: 1 }}> 
            <View>
                <MapScreen/>
                <BlurView style={listEvents.statusBarBlur} intensity={100} />

                <View style={listEvents.fabuttonView}>
                    <Fabutton style = {listEvents.fabutton}/>
                </View>

                <BottomSheet ref={sheetRef} snapPoints={snapPoints} handleIndicatorStyle = {{width: 60, color: 'rgb(211, 211, 211)', alignSelf: 'center'}}>
                    <BottomSheetView>
                        <EventList props = {props}> </EventList>
                    </BottomSheetView>
                </BottomSheet>

            </View> 
        </GestureHandlerRootView>
    )
}