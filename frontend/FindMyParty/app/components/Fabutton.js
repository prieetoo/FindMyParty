import React, { component } from 'react'
import { View, Text, StyleSheet, TouchableWithoutFeedback } from 'react-native'
import { AntDesign, Entypo } from '@expo/vector-icons'

export default class Fabutton extends component {

    animation = new Animated.Value(0);

    toggleMenu = () => {
        const toValue = this.open? 0 : 1
        Animated.spring(this.animation, {
            toValue,
            friction: 6,
        }).start();

        this.open = !this.open;
    }

    render(){

        const locationStyle = {
            transform: [
                { scale: this.animation },
                {
                    translateY: this.animation.interpolate({
                        inputRange: [0, 1],
                        outputRange: [0, -70]
                    })
                }
            ]
        }

        const rotation = {
            transform: [
                {
                    rotate: this.animation.interpolate({
                        inputRange: [0, 1],
                        outputRange: ["0deg", "45deg"]
                    })
                }
            ]
        }

        return (
            <View style={[styles.container, this.props.style]}>
                <TouchableWithoutFeedback>
                <Animated.View style={[styles.button, styles.submenu, locationStyle]}>
                    <Entypo name="location-pin" size={20} color="#FFF"/>
                </Animated.View>
                </TouchableWithoutFeedback>
                
                <TouchableWithoutFeedback onPress={this.toggleMenu}>
                <Animated.View style={[styles.button, styles.menu, rotation]}>
                    <AntDesign name="plus" size={24} color="#FFF"/>
                </Animated.View>
                </TouchableWithoutFeedback>
            </View>
        );
    }
}

const styles = StyleSheet.create ({
    container:{
        alignItems: 'center',
        position: 'absolute'

    },
    button:{
        position: 'absolute',
        width: 60,
        height: 60,
        borderRadius: 60 / 2,
        justifyContent: 'center',
        alignItems: 'center',
        shadowRadius: 1,
        shadowColor: '#00213b',
        shadowOpacity: 0.3,
        shadowOffset: {
            height: 10,
        }
    },
    menu:{
        backgroundColor: '#00213b'
    },
    
    submenu:{
        width: 48,
        height: 48,
        borderRadius: 48 /2,
        backgroundColor: '#00213b'
    }
});