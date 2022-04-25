import React, { useRef } from 'react';
import { StyleSheet, SafeAreaView, Pressable, Text, TextInput, View, Keyboard, TouchableWithoutFeedback} from 'react-native';

function RegisterFormScreen() {

    const ref_input2 = useRef();
    const ref_input3 = useRef();
    const ref_input4 = useRef();

    return (
            <DismissKeyboard>
                    <SafeAreaView style = {styles.container}>
                        <View>

                        </View>

                        <View style = {styles.container}>

                            <View style = {styles.titleBox}>
                                <Text style = {styles.title}>Register using your email address</Text>
                            </View>
                            
                            <View style = {styles.formFieldsBox}>
                                <View>
                                    <TextInput
                                    style = {styles.formFields} 
                                    placeholder = "Email address" 
                                    keyboardType='email-address' 
                                    autoCorrect = {false} 
                                    autoCapitalize='none' 
                                    returnKeyType='next'
                                    onSubmitEditing={() => ref_input2.current.focus()}/>
                                </View>

                            <View style = {styles.formFieldsBox}>
                                <View>
                                    <TextInput
                                    style = {styles.formFields} 
                                    placeholder = "Username" 
                                    keyboardType='default' 
                                    autoCorrect = {false} 
                                    autoCapitalize='none' 
                                    returnKeyType='next'
                                    onSubmitEditing={() => ref_input3.current.focus()}
                                    ref = {ref_input2} />
                                </View>
                                
                                <View>
                                    <TextInput 
                                    style = {styles.formFields}
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
                                    style = {styles.formFields}
                                    placeholder = "Repeat password" 
                                    keyboardType='default' 
                                    autoCorrect = {false} 
                                    autoCapitalize='none' 
                                    returnKeyType='done' 
                                    secureTextEntry = {true}
                                    ref={ref_input4}/>
                                </View>
                            </View>

                            <View style = {styles.registerBox}>
                                <View>
                                    <Pressable style = {styles.register}>
                                        <Text style = {{color: "white", fontSize: 20}}>Register</Text>
                                    </Pressable>
                                </View>
                            </View>
                            
                        </View>
                        </View>

                        <View style = {styles.loginSpace}>
                            <Text> Already have an account?</Text>
                            <Text style = {styles.loginText}> Log in</Text>
                        </View>

                    </SafeAreaView>
            </DismissKeyboard>

    );
}

const DismissKeyboard = ({ children }) => (
    <TouchableWithoutFeedback onPress = {() => Keyboard.dismiss()}> 
    {children}
    </TouchableWithoutFeedback>
    );

const styles = StyleSheet.create({
    container: {
        flex: 3,
        flexDirection: 'column',
        backgroundColor: "white",
        alignItems: 'center',
        justifyContent: 'space-evenly',
        
    },
    statusbar: {
        backgroundColor: "#020",
    },
    titleBox: {
        marginTop: 50,
        marginBottom: 20,
        flexDirection: 'row',
        alignItems: 'center',
    },

    formFieldsBox: {
        flex: 1,
        justifyContent: 'space-evenly',

    },

    title: {
        flex: 1,
        flexDirection: 'row',
        marginHorizontal: 30,
        fontSize: 30,
        fontFamily: 'RalewayLight',
        textAlign: 'center',
    },

    formFields: {
        backgroundColor: 'rgb(248, 248, 248)',
        width: 350,
        height: 50,
        borderRadius: 8,
        paddingHorizontal: 15,
    },

    register: {
        backgroundColor: 'rgb(63, 152, 246)',
        width: 300,
        height: 50,
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 8,
    },

    registerBox: {
        flex: 1,
        marginTop: 20,
        alignItems: 'center',
    },

    loginSpace: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'flex-end',

    },

    loginText: {
        color: 'rgb(63, 152, 246)',
    }
})

export default RegisterFormScreen;