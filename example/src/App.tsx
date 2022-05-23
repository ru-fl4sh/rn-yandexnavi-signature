import * as React from 'react';

import { StyleSheet, View, Button, Linking } from 'react-native';
import { sign } from 'rn-yandexnavi-signature';

const client = '000';
const androidKey = `-----BEGIN PRIVATE KEY-----
KEY HERE
-----END PRIVATE KEY-----`;

export default function App() {
  const openUrl = async () => {
    const url =
      'yandexnavi://build_route_on_map?lat_to=55.680559&lon_to=37.549246';
    const canOpen = await Linking.canOpenURL(url);

    if (canOpen) {
      sign(url, client, androidKey)
        .then((signedUrl) => {
          Linking.openURL(signedUrl);
        })
        .catch((error) => {
          console.warn(error);
          Linking.openURL(url);
        });
    }
  };

  return (
    <View style={styles.container}>
      <Button title="Open URL" onPress={openUrl} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
