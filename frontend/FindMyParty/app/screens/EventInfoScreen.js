import React from 'react';
import {
  ImageBackground,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import colors from '../styles/colors';
import Icon from 'react-native-vector-icons/MaterialIcons';
import { BlurView } from 'expo-blur'
import { listEvents } from '../styles/styles'
import { useNavigation } from '@react-navigation/native';

export default function EventInfoScreen(props){

    const image = {uri: "https://direct.rhapsody.com/imageserver/images/alb.315707992/500x500.jpg"};
    const navigation = useNavigation(); 


  return (
      
    <ScrollView
      showsVerticalScrollIndicator={false}
      contentContainerStyle={{
        backgroundColor: colors.white,
        paddingBottom: 20,
      }}>
          <StatusBar
        barStyle="light-content"
        translucent
        backgroundColor="rgb(255, 255, 255)"
      />
      <ImageBackground style={style.headerImage} source={image}>
        <View style={style.header}>
          <Icon
            name="arrow-back-ios"
            size={28}
            color={colors.white}
            onPress = {() => goToScreen('MapList')}
          />
          <Icon 
            name="border-color" 
            size={28} color={colors.white}
            onPress = {() => goToScreen('EditEvent')}
            />
        </View>
      </ImageBackground>
      <View>
        <View style={style.iconContainer}>
          <Icon name="place" color={colors.white} size={28} />
        </View>
        <View style={{marginTop: 25, paddingHorizontal: 20}}>
          <Text style={{ fontSize: 30, fontWeight: 'bold'}}> </Text>
          <View
            style={{
              marginTop: 10,
              flexDirection: 'row',
              justifyContent: 'space-between',
            }}>
            <View style={{flexDirection: 'row'}}>
              <View style={{flexDirection: 'row'}}>
                <Icon name="star" size={30} color={colors.orange} />
                <Icon name="star" size={30} color={colors.orange} />
                <Icon name="star" size={30} color={colors.orange} />
                <Icon name="star" size={30} color={colors.orange} />
                <Icon name="star" size={30} color={colors.grey} />
              </View>
              <Text style={{fontWeight: 'bold', fontSize: 23, marginLeft: 5, alignSelf: 'center', marginTop: 2,}}>
                4.0
              </Text>
            </View>
            <Text style={{fontSize: 13, color: colors.grey, alignSelf: 'center'}}>365 reviews</Text>
          </View>
          <ScrollView showsVerticalScrollIndicator={true} style={{marginTop: 20, maxHeight: 200}}>
            <Text style={{lineHeight: 20, color: colors.grey}}>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vulputate suscipit nisi at viverra. 
            Fusce vitae cursus velit, sit amet cursus ligula. Integer commodo accumsan magna et scelerisque. 
            Aenean vulputate tortor at orci placerat, elementum porta turpis tempor. Integer dictum leo sagittis 
            metus sagittis malesuada. Duis mollis interdum euismod. Nam sodales feugiat libero. Donec laoreet nulla 
            lacus. Nulla facilisi. Fusce ac nisl imperdiet metus tincidunt maximus nec ac ligula. Ut vel dui id lectus 
            dignissim lobortis. Integer et accumsan nulla. Vivamus a mollis justo.
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vulputate suscipit nisi at viverra. 
            Fusce vitae cursus velit, sit amet cursus ligula. Integer commodo accumsan magna et scelerisque. 
            Aenean vulputate tortor at orci placerat, elementum porta turpis tempor. Integer dictum leo sagittis 
            metus sagittis malesuada. Duis mollis interdum euismod. Nam sodales feugiat libero. Donec laoreet nulla 
            lacus. Nulla facilisi. Fusce ac nisl imperdiet metus tincidunt maximus nec ac ligula. Ut vel dui id lectus 
            dignissim lobortis. Integer et accumsan nulla. Vivamus a mollis justo.
            </Text>
          </ScrollView>
        </View>
        <View
          style={{
            marginTop: 20,
            flexDirection: 'row',
            justifyContent: 'space-between',
            paddingLeft: 20,
            alignItems: 'center',
          }}>
               <Text style={{fontSize: 20, fontWeight: 'bold'}}>
            Participants
          </Text>
          <View style={style.priceTag}>
            <Text
              style={{
                fontSize: 12,
                fontWeight: 'bold',
                color: colors.grey,
                marginLeft: 5,
              }}>
              500 users
            </Text>
          </View>
        </View>
        <View style={style.btn}>
          <Text style={{color: colors.white, fontSize: 18, fontWeight: 'bold'}}>
            Join Now
          </Text>
        </View>
      </View>
    </ScrollView>
  );

  function goToScreen(routeName){
    props.navigation.navigate(routeName)
}

};

const style = StyleSheet.create({
  btn: {
    height: 55,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 40,
    backgroundColor: colors.primary,
    marginHorizontal: 20,
    marginBottom: 20,
    borderRadius: 10,
  },

  priceTag: {
    height: 40,
    alignItems: 'center',
    marginLeft: 40,
    paddingLeft: 20,
    flex: 1,
    backgroundColor: colors.secondary,
    borderTopLeftRadius: 20,
    borderBottomLeftRadius: 20,
    flexDirection: 'row',
  },
  iconContainer: {
    position: 'absolute',
    height: 60,
    width: 60,
    backgroundColor: colors.primary,
    top: -30,
    right: 20,
    borderRadius: 30,
    justifyContent: 'center',
    alignItems: 'center',
  },
  headerImage: {
    height: 400,
    borderBottomRightRadius: 40,
    borderBottomLeftRadius: 40,
    overflow: 'hidden',
  },
  header: {
    marginTop: 60,
    flexDirection: 'row',
    alignItems: 'center',
    marginHorizontal: 20,
    justifyContent: 'space-between',
  },
});
