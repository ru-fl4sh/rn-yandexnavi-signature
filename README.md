# rn-yandexnavi-signature

React Native library for signing yandexnavi url.

> **iOS version is not supported yet.**

## Installation

```sh
npm install rn-yandexnavi-signature
npx pod-install
```

The Key and Client ID you can get from yandex [here](https://forms.yandex.ru/surveys/4902/).

## Usage

```js
import { sign } from 'rn-yandexnavi-signature';
import { Linking } from 'react-native';

// ...

const url = 'yandexnavi://build_route_on_map?lat_to=55.680559&lon_to=37.549246';
const key = 'YOUR KEY HERE';
const client = 'YOUR CLIENT ID HERE';
sign(url, client, key)
  .then((signedUrl) => {
    Linking.openURL(signedUrl);
  })
  .catch((error) => {
    // open without sinature
    Linking.openURL(url);
  });
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
