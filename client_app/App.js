/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import { Navigation } from "react-native-navigation";
import { Provider } from "react-redux";
import {Platform, StyleSheet, Text, View} from 'react-native';

import configureStore from './store/configureStore';
import UserProfile from './screens/UserProfile';
import LoanRequest from './screens/LoanRequest';
import GroupProfiles from './screens/GroupProfiles';
import Friends from './screens/Friends';
import DepositToACircle from './screens/DepositToACircle';
import CurrentCircle from './screens/CurrentCircle';
import SideDrawer from './screens/main/SideDrawer';
import StartMainTabs from './screens/main/StartMainTabs';


const store = configureStore();

Navigation.registerComponent('aionSigma.SideDrawer', () => SideDrawer, store, Provider);
Navigation.registerComponent('aionSigma.StartMainTabs', () => StartMainTabs, store, Provider);

Navigation.registerComponent('aionSigma.UserProfile', () => UserProfile, store, Provider);
Navigation.registerComponent('aionSigma.LoanRequest', () => LoanRequest, store, Provider);
Navigation.registerComponent('aionSigma.GroupProfiles', () => GroupProfiles, store, Provider);
Navigation.registerComponent('aionSigma.Friends', () => Friends, store, Provider);
Navigation.registerComponent('aionSigma.DepositToACircle', () => DepositToACircle, store, Provider);
Navigation.registerComponent('aionSigma.CurrentCircle', () => CurrentCircle, store, Provider);

// Start a App
StartMainTabs();
// Navigation.startSingleScreenApp({
//   screen: {
//     screen: "aionSigma.StartMainTabs"
//   }
// });
// const instructions = Platform.select({
//   ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
//   android:
//     'Double tap R on your keyboard to reload,\n' +
//     'Shake or press menu button for dev menu',
// });

// type Props = {};
// export default class App extends Component<Props> {
//   render() {
//     return (
//       <View style={styles.container}>
//         <Text style={styles.welcome}>Welcome to React Native!</Text>
//         <Text style={styles.instructions}>To get started, edit App.js</Text>
//         <Text style={styles.instructions}>{instructions}</Text>
//       </View>
//     );
//   }
// }

// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//     justifyContent: 'center',
//     alignItems: 'center',
//     backgroundColor: '#F5FCFF',
//   },
//   welcome: {
//     fontSize: 20,
//     textAlign: 'center',
//     margin: 10,
//   },
//   instructions: {
//     textAlign: 'center',
//     color: '#333333',
//     marginBottom: 5,
//   },
// });
