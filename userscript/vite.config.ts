import { defineConfig } from "vite";
import monkey from "vite-plugin-monkey";

export default defineConfig({
  plugins: [
    monkey({
      entry: "src/main.ts",
      userscript: {
        name: "Browser scripts to help AlgoEvent",
        namespace: "https://github.com/traP-jp",
        version: "0.1",
        author: "traP Community",
        match: ["*://*/*"],
        connect: ["algo_event.trap.show"],
        grant: ["GM.setValue", "GM.getValue", "GM.xmlHttpRequest"],
      },
      build: {
        fileName: "algo_event.user.js",
      },
    }),
  ],
});
