import React, { useState, useRef } from 'react'
import { Alert, StyleSheet, SafeAreaView, Pressable, Text, TextInput, View, Keyboard, TouchableWithoutFeedback } from 'react-native'
import { logStyles } from '../styles/styles'
import { CheckBox, SocialIcon, Button } from 'react-native-elements'
import { useNavigation } from '@react-navigation/native';
import userData from '../data/user.json'


export default function RegistroScreen(props) {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [username, setUsername] = useState('')
    const navigation = useNavigation();



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
                                    onChangeText={text => setPassword(text)}
                                    ref={ref_input4}/>
                                </View>
                            </View>

                            <View style = {logStyles.registerBox}>
                                <View>
                                    <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, logStyles.mainButton]} onPress = {() => register(username, email, password, navigation)} >
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}>Register</Text>
                                    </Pressable>
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

    const register = (userUsername, userEmail, userPassword, userNavigation) => {
        
        try { 
            let response = fetch('http://192.168.68.107:8080/user/register', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: userUsername,
                password: userPassword,
                photo: "jaja",
                email: userEmail,
                date_birth: "2001-03-18"
            })
        })
        .then(response => response.json())
        .then(login(userEmail, userPassword, userNavigation))
    }
    catch (error) {
        console.error(error);
     }

    };

    const login = (userEmail, userPassword, navigation) => {
        
        try { 
            let response = fetch('http://192.168.68.107:8080/user/login', {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    password: userPassword,
                    email: userEmail,
                })
            })
            .then(response => response.json())
            .then(data => {
                var result = parseInt(data.result)
                if (result > 0) {
                    userData.email = userEmail; 
                    userData.id = data.result; // Stores variables in a file to enable features such as deleting the account.
                    console.log(userData.email)
                    console.log(userData.id)
                    navigation.navigate('MapList')
                }
                else {
                    if (result == -1) {
                        console.log(data.result)
                        Alert.alert("Existing mail", "The email you entered is already associated to a FindMyParty account. Please, select another email.")
                    }
                    
                    if (result == 0) {
                        console.log(data.result)
                        Alert.alert("Error", "There has been an error. Please, try again.")
                    }
                }
                
            })
    
        }
        catch (error) {
            console.error(error);
         }
    
        };


