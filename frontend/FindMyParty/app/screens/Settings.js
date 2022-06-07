import React from 'react'
import { SafeAreaView, View, Text, Pressable, ScrollView, Image } from 'react-native'
import { listEvents, logStyles } from '../styles/styles'
import { useNavigation } from '@react-navigation/native';
import DeleteAccount from './DeleteUserScreen';

export default function Settings(props){

    const navigation = useNavigation();
   
    return(
           <SafeAreaView>
               <ScrollView style = {listEvents.eventScroll}>
                   <Pressable style = {listEvents.settingsOptions} onPress = {() => navigation.navigate('DeleteAccount')}>
                    <View style = {listEvents.eventDetails}>
                        <Text style = {{fontWeight: 'bold', fontSize: 20,}}> Delete account </Text>
                        <Text style = {{fontSize: 15, paddingTop: 5}}> Delete your account permanently. </Text>
                    </View>
                    <Image source={require('../assets/arrow.png')} style = {listEvents.eventNavigate}/>
                   </Pressable>
                </ScrollView>
           </SafeAreaView>
    )
}