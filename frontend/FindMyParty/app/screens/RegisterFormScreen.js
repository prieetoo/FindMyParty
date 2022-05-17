import React, { useState, useRef } from 'react'
import { StyleSheet, SafeAreaView, Pressable, Text, TextInput, View, Keyboard, TouchableWithoutFeedback } from 'react-native'
import { logStyles } from '../styles/styles'
import { CheckBox, SocialIcon, Button } from 'react-native-elements'

function goToScreen(props, routeName) {
    props.navigation.navigate(routeName)
}

export default function RegistroScreen(props) {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [username, setUsername] = useState('')


    const ref_input2 = useRef();
    const ref_input3 = useRef();
    const ref_input4 = useRef();

    return (
        <DismissKeyboard>
                    <SafeAreaView style = {logStyles.container}>

                        <View style = {logStyles.container}>

                            <View style = {logStyles.titleBoxRegister}>
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
                                    onChangeText={text => setEmail(text)}
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
                                    onChangeText={text => setUsername(text)}
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
                                    onChangeText={text => setPassword(text)}
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
                                    <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, logStyles.mainButton]} onPress = {(username, email, password) => register(username, email, password)} >
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

                            <View style = {logStyles.loginSpace}>
                                <Text style = {{color: 'rgb(255, 255, 255)'}}> Register </Text>
                            </View>
                            
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

    const register = async(username, email, password) => {
        
        try { 
            let response = await fetch('http://192.168.68.102:8080/user/register', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: "prieetoo",
                password: "hola1234",
                photo: "jaja",
                email: "email",
                date_birth: "2001-03-18"
            })
        })
        .then(header => {console.log(JSON.stringify(header))})
        .then(body => {console.log(JSON.stringify(body))})
    }
    catch (error) {
        console.error(error);
     }

    };


