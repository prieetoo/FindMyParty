import React from 'react';
import { StyleSheet } from 'react-native'
import RNPickerSelect from 'react-native-picker-select';
import searchData from '../data/searchFilters.json'

export const PaidDropdown = () => {

    return (
        <RNPickerSelect
            placeholder = {{}}
            style = {pickerSelectStyles}
            onValueChange={(value) => {
              searchData.isPaid = value;
              console.log(searchData.isPaid)
            }}
            items={[
                { label: 'Free', value: false},
                { label: 'Paid', value: true }
            ]}
        />
    );
};

const pickerSelectStyles = StyleSheet.create({
    inputIOS: {
      fontSize: 17,
      color: 'rgb(0, 0, 0)',
      marginLeft: 4,
    },
    inputAndroid: {
      fontSize: 17,
      color: 'rgb(62, 167, 253)',
    },
  });