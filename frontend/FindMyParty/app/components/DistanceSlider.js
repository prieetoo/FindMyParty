import React, { Component } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import { Constants } from 'expo';
import Slider from '@react-native-community/slider';
import searchData from '../data/searchFilters.json'


export default class DistanceSlider extends Component {
  
  constructor(props){
    super(props)
     this.state = { 
       distance: searchData.distance,
       minDistance: 1,
       maxDistance: 10
       }
  }
  

  render() {
    return (
      <View style={styles.container}>
      <Slider style={{ width: 300 }} step={0.25}
         minimumValue={this.state.minDistance} maximumValue={this.state.maxDistance}
         value={this.state.distance} 
         onValueChange={(val) => { 
           this.setState({ distance: val });
          }}
          onSlidingComplete = {(val) => { 
            searchData.distance = val;
            console.log(val);
           }}
        />
        <View style={styles.textCon}>
            <Text style={styles.colorGrey}>{this.state.minDistance} km </Text>
            <Text> {this.state.distance} km </Text>  
            <Text style={styles.colorGrey}>{this.state.maxDistance} km </Text>
        </View>
      </View>
    );
  }
}


const styles = StyleSheet.create({
  container: {
      alignSelf: 'center'
  },
  textCon: {
    width: 320,
    flexDirection: 'row',
    justifyContent: 'space-between'
  },
  colorGrey: {
    color: '#d3d3d3'
  },
  colorYellow: {
    color: 'rgb(252, 228, 149)'
  }
});
    