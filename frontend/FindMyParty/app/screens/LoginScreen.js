import React, { useState, useRef } from 'react'
import { Text, View, SafeAreaView, TouchableWithoutFeedback, Pressable, Keyboard, TextInput, Alert } from 'react-native'
import { logStyles } from '../styles/styles'
import { useNavigation } from '@react-navigation/native';
import userData from '../data/user.json'

export default function LoginScreen(props){

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const ref_input2 = useRef();
    const navigation = useNavigation();



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
                                    onChangeText={text => setEmail(text)}
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
                                    <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, logStyles.mainButton]} onPress = {() => login(email, password, navigation)}> 
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}> Log in </Text>
                                    </Pressable>
                                </View>
                            </View>
                            
                        </View>

                        <View style = {logStyles.loginSpace}/>

                    </SafeAreaView>
        </DismissKeyboard>
)
    
}

const DismissKeyboard = ({ children }) => (
    <TouchableWithoutFeedback onPress = {() => Keyboard.dismiss()}> 
    {children}
    </TouchableWithoutFeedback>
    );

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
                    Alert.alert("Wrong password", "The password you entered is not correct. Please, type it again.")
                }

                if (result == -2) {
                    console.log(data.result)
                    Alert.alert("Wrong email", "The email you entered is not registered. Have you signed up yet?")
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
