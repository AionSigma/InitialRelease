import React, { Component } from "react";
import { View, Text, Dimensions, StyleSheet } from "react-native";

const styles = StyleSheet.create({
  container: {
    paddingTop: 22,
    backgroundColor:'#fff',
    flex: 1
  }
});

class SideDrawer extends Component {
  render() {
    return (
      <View
        style={[
          styles.container,
          { width: Dimensions.get("window").width * 0.8 }
        ]}
      >
        <Text>SideDrawer</Text>
      </View>
    );
  }
}



export default SideDrawer;