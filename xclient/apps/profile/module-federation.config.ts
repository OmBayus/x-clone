import { ModuleFederationConfig } from '@nx/webpack';

const config: ModuleFederationConfig = {
  name: 'profile',
  exposes: {
    './Module': 'apps/profile/src/app/remote-entry/entry.module.ts',
  },
};

export default config;
