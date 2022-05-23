import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'rn-yandexnavi-signature' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const RnYandexnaviSignature = NativeModules.RnYandexnaviSignature
  ? NativeModules.RnYandexnaviSignature
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function sign(
  url: string,
  client: string,
  key: string
): Promise<string> {
  return RnYandexnaviSignature.sign(url, client, key);
}
