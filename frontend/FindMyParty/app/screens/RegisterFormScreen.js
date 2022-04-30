import React, { useState, useRef } from 'react'
import { StyleSheet, SafeAreaView, Pressable, Text, TextInput, View, Keyboard, TouchableWithoutFeedback } from 'react-native'
import { logStyles } from '../styles/styles'
import { CheckBox, SocialIcon, Button } from 'react-native-elements'

function goToScreen(props, routeName) {
    props.navigation.navigate(routeName)
}

export default function RegistroScreen(props) {

    const ref_input2 = useRef();
    const ref_input3 = useRef();
    const ref_input4 = useRef();

    return (
        <DismissKeyboard>
                    <SafeAreaView style = {logStyles.container}>
                        <View>

                        </View>

                        <View style = {logStyles.container}>

                            <View style = {logStyles.titleBox}>
                                <Text style = {logStyles.title}>Register using your email address</Text>
                            </View>
                            
                            <View style = {logStyles.formFieldsBoxRegister}>
                                <View>
                                    <TextInput
                                    style = {logStyles.formFields} 
                                    placeholder = "Email address" 
                                    keyboardType='email-address' 
                                    autoCorrect = {false} 
                                    autoCapitalize='none' 
                                    returnKeyType='next'
                                    onSubmitEditing={() => ref_input2.current.focus()}/>
                                </View>

                                <View>
                                    <TextInput
                                    style = {logStyles.formFields} 
                                    placeholder = "Username" 
                                    keyboardType='default' 
                                    autoCorrect = {false} 
                                    autoCapitalize='none' 
                                    returnKeyType='next'
                                    onSubmitEditing={() => ref_input3.current.focus()}
                                    ref = {ref_input2} />
                                </View>
                                
                                <View>
                                    <TextInput 
                                    style = {logStyles.formFields}
                                    placeholder = "Password" 
                                    keyboardType='default' 
                                    autoCorrect = {false} 
                                    autoCapitalize='none' 
                                    returnKeyType='next' 
                                    secureTextEntry = {true}
                                    onSubmitEditing={() => ref_input4.current.focus()}
                                    ref={ref_input3}/>
                                </View>

                                <View>
                                    <TextInput 
                                    style = {logStyles.formFields}
                                    placeholder = "Repeat password" 
                                    keyboardType='default' 
                                    autoCorrect = {false} 
                                    autoCapitalize='none' 
                                    returnKeyType='done' 
                                    secureTextEntry = {true}
                                    ref={ref_input4}/>
                                </View>
                            </View>

                            <View style = {logStyles.registerBox}>
                                <View>
                                    <Pressable style = {logStyles.mainButton}>
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}>Register</Text>
                                    </Pressable>
                                </View>

                                <View styles={logStyles.containerSocial}>
                                <SocialIcon
                                    style = {logStyles.buttonSocialIcon}
                                    title = 'Continue with Google' button 
                                    type='google-plus-official'
                                    fontFamily='RalewayUI'
                                    />
                            </View>

                            </View>
                            
                        </View>

                        <View style = {logStyles.loginSpace}>
                            <Text> Already have an account?</Text>
                            <Text style = {logStyles.loginText} onPress={()=> goToScreen(props, 'Login')}> Log in </Text>
                        </View>

                    </SafeAreaView>
            </DismissKeyboard>
    )
}

const DismissKeyboard = ({ children }) => (
    <TouchableWithoutFeedback onPress = {() => Keyboard.dismiss()}> 
    {children}
    </TouchableWithoutFeedback>
    );

