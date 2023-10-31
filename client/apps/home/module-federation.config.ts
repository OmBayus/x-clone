import { ModuleFederationConfig } from '@nx/webpack';

const config: ModuleFederationConfig = {
  name: 'home',
  exposes: {
    './Module': 'apps/home/src/app/remote-entry/entry.module.ts',
  },
};

export default config;
