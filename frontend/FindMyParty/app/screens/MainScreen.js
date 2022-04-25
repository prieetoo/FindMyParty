import React from 'react';
import {StyleSheet, View} from 'react-native';
import {GestureHandlerRootView} from 'react-native-gesture-handler'; 

function MainScreen() {

    return (
        <GestureHandlerRootView>
            <View style = {styles.container}>
                
            </View>
        </GestureHandlerRootView>
    );
}

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
    
})

export default MainScreen;