import React, { useState, useRef } from 'react'
import { Text, View, SafeAreaView, TouchableWithoutFeedback, Pressable, Keyboard, TextInput, Alert } from 'react-native'
import { logStyles, listEvents } from '../styles/styles'
import { useNavigation } from '@react-navigation/native';
import { DayDropdown } from '../components/DayPicker';
import Slider from '@react-native-community/slider';
import AssistantSlider from '../components/AssistantSlider'
import DistanceSlider from '../components/DistanceSlider';
import { PaidDropdown } from '../components/PaidPicker';
import { TagDropdown } from '../components/TagPicker';

export default function FilterScreen(props){

    /*
    { label: 'Up next', value: 'upNext'},
    { label: 'Distance', value: 'distance' },
    { label: 'Assistants', value: 'assistants' },
    { label: 'Host rating', value: 'hostRating'},
    { label: 'Paid', value: 'isPaid'},
    tags
    */

    const navigation = useNavigation();



    return(
    <DismissKeyboard>
        <SafeAreaView style = {listEvents.containerFilters}>
            <View style = {listEvents.filterDropdownView}>
                <Text style = {{fontSize: 20, fontWeight: 'bold', paddingBottom: 10}}> Day of the week </Text>
                <DayDropdown/>
            </View>
            
            <View style = {listEvents.filterDropdownView}>
                <Text style = {{fontSize: 20, fontWeight: 'bold', paddingBottom: 10}}> Distance </Text>
                <DistanceSlider/>
            </View>

            <View style = {listEvents.filterDropdownView}>
                <Text style = {{fontSize: 20, fontWeight: 'bold', paddingBottom: 10}}> Assistants </Text>
                <AssistantSlider/>
            </View>

            <View style = {listEvents.filterDropdownView}>
                <Text style = {{fontSize: 20, fontWeight: 'bold', paddingBottom: 10}}> Paid </Text>
                <PaidDropdown/>
            </View>

            <View style = {listEvents.filterDropdownView}>
                <Text style = {{fontSize: 20, fontWeight: 'bold', paddingBottom: 10}}> Tag </Text>
                <TagDropdown/>
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

const login = (userEmail, userPassword, navigation) => {
        
    try { 
        let response = fetch('http://172.20.10.5:8080/user/login', {
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
                deleteAccount(result, navigation)
                console.log("Account deleted")
            }
            else {
                if (result == -1) {
                    console.log(data.result)
                    Alert.alert("Wrong password", "The password you entered is not correct. Please, type it again.")
                }

                if (result == -2) {
                    console.log(data.result)
                    Alert.alert("Wrong email", "The email you entered is not correct.")
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

const deleteAccount = (userID, navigation) => {
    try { 
        let response = fetch('http://192.168.68.107:8080/user/eliminate/83', { method: 'GET' })
        .then(response => response.json())
        .then(data => {
            var result = parseInt(data.result)
            if (result == 1) {
                console.log("Deleted account")
                Alert.alert("Account deleted", "Your account has been successfully deleted.")
                navigation.navigate('Welcome')
            }
            else{
                Alert.alert("Error", "There has been an error. Please, try again.")
            }
        })
    }
    
    catch (error) {
        console.error(error);
     }
};
