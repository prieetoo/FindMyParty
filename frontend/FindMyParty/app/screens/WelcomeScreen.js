import React from 'react'
import { useNavigation } from '@react-navigation/native';
import { Text, Image, View, SafeAreaView, Pressable, TextInput } from 'react-native'
import { logStyles } from '../styles/styles'
import { SocialIcon } from 'react-native-elements'

export default function WelcomeScreen(props){

    const navigation = useNavigation(); 

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
                                    <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, logStyles.mainButton]} onPress={() => navigation.navigate('Login')}> 
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}> Log in </Text>
                                    </Pressable>
                                </View>

                                <View>
                                    <Pressable style = {logStyles.secondaryButton} onPress = {() => goToScreenSecondary('Registro')}> 
                                        {({ pressed }) => ( <Text style={[{ color: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)' }, logStyles.registerSecondary]}> Register </Text>)}
                                    </Pressable>
                                </View>

                               {/*style = {{color: 'rgb(63, 152, 246)', fontSize: 20, fontFamily: 'RalewayUI',}} */}

                            </View>
                            
                        </View>
                    </SafeAreaView>
)
    
}
