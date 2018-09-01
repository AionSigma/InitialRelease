import { Navigation } from 'react-native-navigation';
import Icon from 'react-native-vector-icons/Ionicons';

const StartTabs = () => {
    Promise.all([
        Icon.getImageSource("md-person", 30),
        Icon.getImageSource("md-people", 30),
        Icon.getImageSource("md-menu", 30)
    ]).then(sources => {
        
        Navigation.startTabBasedApp({
            tabs: [
                {
                    screen: "aionSigma.UserProfile",
                    label: "User Profile",
                    title: "User Profile",
                    icon: sources[0],
                    navigatorButtons: {
                        leftButtons: [
                            {
                                icon: sources[2],
                                title: "Menu",
                                id: "sideDrawerToggle"
                            }
                        ]
                    }
                },
                {
                    screen: "aionSigma.Friends",
                    label: "Friends",
                    title: "Friends",
                    icon: sources[1],
                    navigatorButtons: {
                        leftButtons: [
                            {
                                icon: sources[2],
                                title: "Menu",
                                id: "sideDrawerToggle"
                            }
                        ]
                    }
                }
            ],
            drawer: {
                left: {
                    screen: "aionSigma.SideDrawer"
                }
            }
        });
    });
};

export default StartTabs;