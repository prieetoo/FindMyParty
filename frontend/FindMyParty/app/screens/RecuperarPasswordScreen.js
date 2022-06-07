import React from 'react'
import { Text, View, SafeAreaView, TextInput, Pressable, TouchableWithoutFeedback, Keyboard} from 'react-native'
import { logStyles } from '../styles/styles'
import MyTextInput from '../components/MyTextInput'
import ToolBar from '../components/ToolBar'
import color from '../styles/colors'

function goToScreen(props, routeName){
    props.navigation.navigate(routeName)
}

export default function RecuperarPasswordScreen(props){

    return(
        <DismissKeyboard>
           <SafeAreaView style = {logStyles.container}>
                        <View>

                        </View>

                        <View style = {logStyles.container}>

                            <View style = {logStyles.titleBox}>
                                <Text style = {logStyles.title}> Recover your password </Text>
                            </View>
                            
                            <View style = {logStyles.formFieldsBoxLogin}>

                                <View>
                                    <TextInput
                                    style = {logStyles.formFields} 
                                    placeholder = "Email address used for registration" 
                                    keyboardType='email-address' 
                                    autoCorrect = {false} 
                                    autoCapitalize='none' 
                                    returnKeyType='done'/>
                                </View>
                                
                            </View>

                            <View style = {logStyles.loginBox}>
                                <View>
                                    <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, logStyles.mainButton]}> 
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}> Recover password </Text>
                                    </Pressable>
                                </View>
                            </View>
                            
                        </View>

                        <View style = {logStyles.loginSpace}>
                            <Text style = {{color: 'white'}}> placeholder </Text>
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