import React, { useState, useRef} from 'react'
import { Text, View, SafeAreaView, TouchableWithoutFeedback, Pressable, Keyboard, TextInput } from 'react-native'
import { logStyles } from '../styles/styles'
import { SocialIcon } from 'react-native-elements'



export default function CreateEventScreen() {
   
    return(
        <DismissKeyboard>
           <SafeAreaView style = {logStyles.container}>
                        <View>

                        </View>

                        <View style = {logStyles.container}>

                            <View style = {logStyles.titleBox}>
                                <Text style = {logStyles.title}>Create an Event</Text>
                            </View>
                            
                            <View style = {logStyles.formFieldsBoxLogin}>

                                <View>
                                    <TextInput
                                    style = {logStyles.formFields} 
                                    placeholder = "Event title" 
                                    keyboardType='default' 
                                    autoCorrect = {true} 
                                    autoCapitalize='sentences' 
                                    returnKeyType='next'
                                    onSubmitEditing={() => ref_input2.current.focus()}/>
                                </View>
                                
                                <View>
                                    <TextInput
                                    style = {logStyles.formFields} 
                                    placeholder = "Description" 
                                    keyboardType='default' 
                                    multiline={true}
                                    autoCorrect = {true} 
                                    autoCapitalize='sentences' 
                                    returnKeyType='next'
                                    onSubmitEditing={() => ref_input2.current.focus()}/>
                                </View>
                                
                            </View>

                            <View style = {logStyles.loginBox}>
                                <View>
                                    <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, logStyles.mainButton]} onPress = {() => goToScreen('MapList')}> 
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}> Crear Evenento </Text>
                                    </Pressable>
                                </View>

                            </View>
                            
                        </View>

                    </SafeAreaView>
        </DismissKeyboard>
    )
}

function CrearEvenento(){
    goToScreen('Main')
}

function goToScreen(routeName){
    props.navigation.navigate(routeName)
}


const DismissKeyboard = ({ children }) => (
<TouchableWithoutFeedback onPress = {() => Keyboard.dismiss()}> 
{children}
</TouchableWithoutFeedback>
);