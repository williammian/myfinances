import React from 'react'

import Login from '../views/login'
import Home from '../views/home'
import CadastroUsuario from '../views/cadastroUsuario'
import ConsultaLancamentos from '../views/lancamentos/consulta-lancamentos'
import CadastroLancamentos from '../views/lancamentos/cadastro-lancamentos'
import { AuthConsumer } from '../main/provedorAutenticacao'

import { Route, Switch, HashRouter, Redirect } from 'react-router-dom'

function RotaAutenticada( { component : Component, isUsuarioAutenticado, ...props } ) {
    return(
        <Route { ...props } render= { (componentProps) => {
            if(isUsuarioAutenticado) {
                return(
                    <Component {...componentProps} />
                )
            }else {
                return(
                    <Redirect to={ { pathname: '/login', state: { from: componentProps.location } } } />
                )
            }
        } } />
    )
}

function Rotas(props){
    return(
        <HashRouter>
            <Switch>
                <Route exact path="/login" component={Login} />
                <Route exact path="/cadastro-usuarios" component={CadastroUsuario} />

                <RotaAutenticada exact isUsuarioAutenticado={props.isUsuarioAutenticado} path="/home" component={Home} />
                <RotaAutenticada exact isUsuarioAutenticado={props.isUsuarioAutenticado} path="/consulta-lancamentos" component={ConsultaLancamentos} />
                <RotaAutenticada exact isUsuarioAutenticado={props.isUsuarioAutenticado} path="/cadastro-lancamentos/:id?" component={CadastroLancamentos} />
            </Switch>
        </HashRouter>
    )
}

export default () => (
    <AuthConsumer>
        { (context) => (<Rotas isUsuarioAutenticado={context.isAutenticado} />) }
    </AuthConsumer>
)

