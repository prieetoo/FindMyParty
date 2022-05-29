import React, {useRef} from 'react'
import { Text, View, SafeAreaView, TouchableWithoutFeedback, Pressable, Keyboard, TextInput, StyleSheet } from 'react-native'
import { logStyles } from '../styles/styles'

export default function CreateEventScreen(props){

    const ref_input2 = useRef();
    const ref_input3 = useRef();
   
    return(
        <DismissKeyboard>
           <SafeAreaView style = {logStyles.container}>
                        <View>

                        </View>

                        <View style = {styles.container}>
                            
                            <View style = {logStyles.formFieldsBoxLogin}>

                                <View>
                                    <TextInput
                                    style = {logStyles.formFields} 
                                    placeholder = "Event title" 
                                    keyboardType='default' 
                                    autoCorrect = {true} 
                                    autoCapitalize='sentences' 
                                    returnKeyType='next'
                                    onSubmitEditing={() => ref_input2.current.focus()}/>
                                </View>

                                <View>
                                    <TextInput
                                    style = {logStyles.formFields} 
                                    placeholder = "Description" 
                                    keyboardType='default' 
                                    autoCorrect = {true} 
                                    autoCapitalize='sentences' 
                                    ref={ref_input3}
                                    returnKeyType='done' />
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
                                    ref={ref_input2}
                                    onSubmitEditing={() => ref_input3.current.focus()}/>
                                </View>
                                
                            </View>

                            <View style = {logStyles.loginBox}>
                                <View>
                                    <Pressable style = {({ pressed }) => [{ backgroundColor: pressed ? 'rgb(62, 167, 253)' : 'rgb(63, 152, 246)'}, logStyles.mainButton]} onPress = {() => goToScreen('MapList')}> 
                                        <Text style = {{color: "white", fontSize: 20, fontFamily: 'RalewayUI',}}> Edit event </Text>
                                    </Pressable>
                                </View>

                            </View>
                            
                        </View>

                    </SafeAreaView>
        </DismissKeyboard>
    )

    function EditarEvenento(){
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

const styles = StyleSheet.create({
    container: {
        flex: 3,
        paddingTop: 10,
        flexDirection: 'column',
        backgroundColor: "white",
        alignItems: 'center',
        justifyContent: 'space-evenly',
        
    },
})