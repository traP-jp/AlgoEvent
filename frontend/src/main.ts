import { LitElement, css, html } from "lit";
import { customElement } from "lit/decorators.js";

@customElement("algo-app")
export class AlgoApp extends LitElement {
  static styles = css`
    :host {
      display: block;
      font-family: system-ui, sans-serif;
      max-width: 1200px;
      margin: 0 auto;
      padding: 2rem;
    }

    h1 {
      color: #1a1a1a;
    }
  `;

  render() {
    return html`
      <h1>AlgoEvent</h1>
      <p>競プロイベント情報まとめサイト</p>
    `;
  }
}

declare global {
  interface HTMLElementTagNameMap {
    "algo-app": AlgoApp;
  }
}
