import React from 'react'
import { Text, Image, View, SafeAreaView, Pressable, TextInput } from 'react-native'
import { logStyles } from '../styles/styles'
import { SocialIcon } from 'react-native-elements'

export default function WelcomeScreen(props){

    return(
           <SafeAreaView style = {logStyles.container}>
               
                        <View style = {logStyles.container}>

                            <View style = {logStyles.titleBox}>
                                <Image source = { require('../assets/icon.png')} style = {{width: 300, height: 300}} />
                            </View>

                            <Text style = {logStyles.title}> Welcome </Text>
                            
                            <View style = {logStyles.proceedBox}>

                                <View style={logStyles.containerSocial}>
                                    <SocialIcon
                                        style = {logStyles.buttonSocialIcon}
                                        title = 'Continue with Google' button 
                                        type='google-plus-official'
                                        fontFamily='RalewayUI'
                                        />
                                </View>

                                <View>
                                    <Pressable style = {logStyles.mainButton} onPress = {(props) => goToScreen(props, 'Login')}> 
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}> Log in </Text>
                                    </Pressable>
                                </View>

                                <View>
                                    <Pressable style = {logStyles.secondaryButton} onPress = {() => goToScreen('Registro')}> 
                                        <Text style = {{color: 'rgb(63, 152, 246)', fontSize: 20, fontFamily: 'RalewayUI',}}> Register </Text>
                                    </Pressable>
                                </View>

                            </View>
                            
                        </View>
                    </SafeAreaView>
)

    function goToScreen(routeName){
        props.navigation.navigate(routeName)
    }
    
}
