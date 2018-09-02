import { Navigation } from 'react-native-navigation';
import Icon from 'react-native-vector-icons/Ionicons';

const leftButtons =(sources)=> [
    {
        icon: sources[2],
        title: "Menu",
        id: "sideDrawerToggle"
    }
];

const StartTabs = () => {
    Promise.all([
        Icon.getImageSource("md-person", 20),
        Icon.getImageSource("md-people", 20),
        Icon.getImageSource("md-menu", 20)
    ]).then(sources => {
        
        Navigation.startTabBasedApp({
            tabs: [
                {
                    screen: "aionSigma.UserProfile",
                    label: "User Profile",
                    title: "User Profile",
                    icon: sources[0],
                    navigatorButtons: {
                        leftButtons:leftButtons(sources)
                    }
                },
                {
                    screen: "aionSigma.Friends",
                    label: "Friends",
                    title: "Friends",
                    icon: sources[1],
                    navigatorButtons: {
                        leftButtons:leftButtons(sources)
                    }
                }
            ],
            drawer: {
                left: {
                    screen: "aionSigma.SideDrawer"
                }
             },
            // tabsStyle: { // optional, add this if you want to style the tab bar beyond the defaults
            //     tabBarButtonColor: '#ffff00', // optional, change the color of the tab icons and text (also unselected). On Android, add this to appStyle
            //     tabBarSelectedButtonColor: '#ff9900', // optional, change the color of the selected tab icon and text (only selected). On Android, add this to appStyle
            //     tabBarBackgroundColor: '#551A8B', // optional, change the background color of the tab bar
            //     initialTabIndex: 0, // optional, the default selected bottom tab. Default: 0. On Android, add this to appStyle
            // },
        });
    });
};

export default StartTabs;