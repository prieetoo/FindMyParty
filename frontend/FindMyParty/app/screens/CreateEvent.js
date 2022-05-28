import React, {useRef, useState} from 'react'
import { Text, View, SafeAreaView, TouchableWithoutFeedback, Pressable, Keyboard, TextInput, StyleSheet } from 'react-native'
import { logStyles, listEvents } from '../styles/styles'
import Geocoder from 'react-native-geocoding';
import eventCreationData from '../data/eventCreation.json'
import { format } from "date-fns";
import RNDateTimePicker from '@react-native-community/datetimepicker';
import { TagDropdown } from '../components/TagPicker';

export default function CreateEventScreen(props){

    Geocoder.init("AIzaSyBOfla64RGVuqFlM5JAsVQuFKYzpAlEFl8");

    const ref_input2 = useRef();
    const ref_input3 = useRef();
    const [date, setDate] = useState(new Date());

    const saveTitle = title => {
        eventCreationData.name = title;
    };

    const saveDescription = description => {
        eventCreationData.description = description;
    };

    const saveDate = (event, selectedDate) => {
        const currentDate = selectedDate;
        // setDate(currentDate);
        const newDate = format(currentDate, "yyyy-MM-dd HH:mm:ss")
        eventCreationData.date_time = newDate;
        console.log(eventCreationData.date_time);
      };
    

   
    return(
        <DismissKeyboard>
           <SafeAreaView style = {logStyles.container}>
                        <View>

                        </View>

                        <View style = {logStyles.container}>

                            <View style = {logStyles.titleBox}>
                                <Text style = {logStyles.title}>Create an Event</Text>
                            </View>
                            
                            <View style = {createEventStyles.formFields}>

                                <View>
                                    <TextInput
                                    style = {logStyles.formFields} 
                                    placeholder = "Event title" 
                                    keyboardType='default' 
                                    autoCorrect = {true} 
                                    autoCapitalize='sentences' 
                                    returnKeyType='next'
                                    onSubmitEditing = { newTitle => { saveTitle(newTitle.nativeEvent.text); ref_input2.current.focus()}}
                                    />
                                </View>

                                <View>
                                    <TextInput
                                    style = {createEventStyles.formFieldDescription} 
                                    placeholder = "Description" 
                                    keyboardType='default' 
                                    autoCorrect = {true} 
                                    multiline = {true}
                                    autoCapitalize='sentences' 
                                    ref={ref_input2}
                                    returnKeyType='done'
                                    onSubmitEditing = { newDescription => { saveTitle(newDescription.nativeEvent.text); ref_input3.current.focus()}} 
                                    />
                                </View>
                                
                                <View>
                                    <TextInput
                                    style = {logStyles.formFields} 
                                    placeholder = "Address" 
                                    keyboardType='default'
                                    autoComplete='postal-address-locality' 
                                    autoCorrect = {true} 
                                    autoCapitalize='sentences' 
                                    returnKeyType='next'
                                    ref={ref_input3}
                                    onSubmitEditing={ newAddress => validateAddress(newAddress.nativeEvent.text)}/>
                                </View>

                                <View style = {createEventStyles.tagDropdown}>
                                    <Text style = {{fontWeight: 'bold', fontSize: 16, color: 'rgb(150, 150, 150)'}}> Tag </Text>
                                    <TagDropdown/>
                                </View>

                                <View style = {{flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between'}}>
                                
                                    <Text style = {{fontWeight: 'bold', fontSize: 16, color: 'rgb(150, 150, 150)'}}> Date and time </Text>

                                    <RNDateTimePicker 
                                    mode='datetime' 
                                    value={date} 
                                    onChange = {saveDate}
                                    style = {{ width: 200, alignSelf: 'flex-end'}}/>

                                </View>

                            </View>

                            <View style = {logStyles.loginBox}>
                                <View>
                                    <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, logStyles.mainButton]} onPress = {() => console.log(eventCreationData.name)}> 
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}> Create event </Text>
                                    </Pressable>
                                </View>

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

const validateAddress = address => {

    console.log(address);

    Geocoder.from(address)
    .then(json => {
        var location = json.results[0].geometry.location;
        eventCreationData.latitude = location.lat.valueOf();
        eventCreationData.longitude = location.lng.valueOf();
        
        console.log(location.lat.valueOf())
        console.log(eventCreationData.latitude)

        console.log(location.lng.valueOf())
        console.log(eventCreationData.longitude)
    })
    .catch(error => console.warn(error));

}

const createEventStyles = StyleSheet.create({
    formFields: {
        flex: 4,
        justifyContent: 'space-around',
        paddingBottom: 5,
    },

    formFieldDescription: {
        backgroundColor: 'rgb(248, 248, 248)',
        width: 350,
        height: 100,
        borderRadius: 8,
        paddingHorizontal: 15,
        fontFamily: 'RalewayUI',
        paddingTop: 15,
    },

    tagDropdown: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
    },
})