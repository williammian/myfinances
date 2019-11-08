import React from 'react';

class App extends React.Component {

  state = {
    nome: ''
  }

  render() {
    return(
      <div>
        <label>Nome:</label>
        <input type="text" value={this.state.nome} 
               onChange={(e) => this.setState({nome: e.target.value})}/>

        O nome digitado foi {this.state.nome}
      </div>
    )
  }

}

export default App;
