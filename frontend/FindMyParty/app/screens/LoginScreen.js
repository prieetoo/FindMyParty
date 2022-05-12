import React, { useState, useRef } from 'react'
import { Text, View, SafeAreaView, TouchableWithoutFeedback, Pressable, Keyboard, TextInput } from 'react-native'
import { logStyles } from '../styles/styles'
import { SocialIcon } from 'react-native-elements'

export default function LoginScreen(props){

  
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const ref_input2 = useRef();

    return(
    <DismissKeyboard>
           <SafeAreaView style = {logStyles.container}>
                        <View>

                        </View>

                        <View style = {logStyles.container}>

                            <View style = {logStyles.titleBox}>
                                <Text style = {logStyles.title}>Log in using your account</Text>
                            </View>
                            
                            <View style = {logStyles.formFieldsBoxLogin}>

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
                                    placeholder = "Password" 
                                    keyboardType='default' 
                                    autoCorrect = {false} 
                                    autoCapitalize='none' 
                                    returnKeyType='next' 
                                    onChangeText={text => setPassword(text)}
                                    secureTextEntry = {true}
                                    ref={ref_input2}
                                    />
                                </View>
                                
                            </View>

                            <View style = {logStyles.loginBox}>
                                <View>
                                    <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, logStyles.mainButton]} onPress = {() => goToScreen('MapList')}> 
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}> Log in </Text>
                                    </Pressable>
                                </View>

                                <View style={logStyles.containerSocial} >
                                    <SocialIcon
                                        onPress={()=> goToScreen('CreateEvent')}
                                        style = {logStyles.buttonSocialIcon}
                                        title = 'Continue with Google' button 
                                        type='google-plus-official'
                                        fontFamily='RalewayUI'
                                        />
                                </View>
                            </View>
                            
                        </View>

                        <View style = {logStyles.loginSpace}>
                            <Text style = {logStyles.loginText} onPress={()=> goToScreen('RecuperarPassword')}> Forgot your password? </Text>
                        </View>

                    </SafeAreaView>
        </DismissKeyboard>
)

    function iniciarSesion(){
        loginAction({
            type:'sign', data:{
                email, password
            }
        })
        goToScreen('Main')
    }

    function goToScreen(routeName){
        props.navigation.navigate(routeName)
    }
    
}

const DismissKeyboard = ({ children }) => (
    <TouchableWithoutFeedback onPress = {() => Keyboard.dismiss()}> 
    {children}
    </TouchableWithoutFeedback>
    );