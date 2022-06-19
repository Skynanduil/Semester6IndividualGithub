import React from "react";
import {getFunctionCalledCountFromFAAS} from '../api/FAAS_API_CALLS'

class FAAS extends React.Component{
    constructor(props){
        super(props);
        this.state = {count: 0};

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event){
        event.preventDefault();
        getFunctionCalledCountFromFAAS().then(
            res => {
                alert(res.data.message);
                this.setState({count: res.data.count})
            }
        );
    }

    render(){
        return(
            <form onSubmit={this.handleSubmit}>
                <label>Count: {this.state.count}</label>
                <button type= "submit">Call FAAS function</button>
            </form>
        )
    }
}

export default FAAS;