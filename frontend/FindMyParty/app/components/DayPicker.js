import React from 'react';
import { StyleSheet } from 'react-native'
import RNPickerSelect from 'react-native-picker-select';
import searchData from '../data/searchFilters.json'

export const DayDropdown = () => {

    const placeholder = {
        label: 'Select a day of the week...',
        value: null,
        color: 'black',
      };

    return (
        <RNPickerSelect
            placeholder = {placeholder}
            style = {pickerSelectStyles}
            onValueChange={(value) => {
              searchData.dayOfTheWeek = value;
              console.log(searchData.dayOfTheWeek)
            }}
            items={[
                { label: 'Monday', value: 'monday'},
                { label: 'Tuesday', value: 'tuesday' },
                { label: 'Wednesday', value: 'wednesday' },
                { label: 'Thursday', value: 'thursday'},
                { label: 'Friday', value: 'friday'},
                { label: 'Saturday', value: 'saturday'},
                { label: 'Sunday', value: 'sunday'}
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