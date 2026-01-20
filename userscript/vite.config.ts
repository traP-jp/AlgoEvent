import { defineConfig } from 'vite'
import monkey from 'vite-plugin-monkey'

export default defineConfig({
  plugins: [
    monkey({
      entry: 'src/main.ts',
      userscript: {
        name: 'Browser scripts to help AlgoEvent',
        namespace: 'https://github.com/traP-jp',
        author: 'traP-jp',
        version: '0.1',
        match: ['*://*/*'],
      },
      build: {
        fileName: 'algoevent.user.js',
      },
    }),
  ],
})
