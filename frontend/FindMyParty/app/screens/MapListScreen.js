import React, {useCallback, useRef, useState} from 'react'
import { Text, Image, View, SafeAreaView, Pressable, TextInput, StatusBar } from 'react-native'
import { GestureHandlerRootView } from 'react-native-gesture-handler'
import { logStyles, mapListEvents } from '../styles/styles'
import BottomSheet, { BottomSheetView } from '@gorhom/bottom-sheet'
import { MapScreen } from '../components/Map'

export default function MapListScreen(props){

    const sheetRef = useRef(null);
    const [isOpen, setIsOpen] = useState(true);
      
    const snapPoints = ["16%", "32%", "100%"];

    return(

        <GestureHandlerRootView style = {{ flex: 1 }}> 
            <View>
                <StatusBar backgroundColor={'rgb(255,255,255)'}/>
                <MapScreen/>
                <BottomSheet ref={sheetRef} snapPoints={snapPoints} handleIndicatorStyle = {{width: 60, color: 'rgb(211, 211, 211)', alignSelf: 'center'}}>
                    <BottomSheetView>
                        <Text style = {mapListEvents.title}> Hello! </Text>  
                    </BottomSheetView>
                </BottomSheet>
            </View> 
        </GestureHandlerRootView>
    )
}