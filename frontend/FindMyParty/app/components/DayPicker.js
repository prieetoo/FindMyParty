import React from 'react';
import { StyleSheet } from 'react-native'
import RNPickerSelect from 'react-native-picker-select';
import searchData from '../data/searchFilters.json'

export const DayDropdown = () => {

    const val = searchData.dayOfTheWeek;

    const placeholder = {
        label: 'Select a day of the week...',
        value: null,
        color: 'black',
      };

    return (
        <RNPickerSelect
            placeholder = {placeholder}
            style = {pickerSelectStyles}
            onValueChange = {(value) => {
              searchData.dayOfTheWeek = value;
              console.log(searchData.dayOfTheWeek)
            }}
            items={[
                { label: 'Monday', value: 0 },
                { label: 'Tuesday', value: 1 },
                { label: 'Wednesday', value: 2 },
                { label: 'Thursday', value: 3 },
                { label: 'Friday', value: 4 },
                { label: 'Saturday', value: 5 },
                { label: 'Sunday', value: 6 }
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