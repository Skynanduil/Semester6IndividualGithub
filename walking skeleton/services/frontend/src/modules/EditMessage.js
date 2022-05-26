import React from "react";
import {editMessage} from "../functions/functions";

class EditMessage extends React.Component{
    constructor(props){
        super(props);
        this.state = {id: '', title: 'Hello World', content: 'Hello World'};

        this.handleTitleChange = this.handleTitleChange.bind(this);
        this.handleContentChange = this.handleContentChange.bind(this);
        this.handleIdChange = this.handleIdChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleTitleChange(event) {
        this.setState({title: event.target.value});
    }

    handleContentChange(event) {
        this.setState({content: event.target.value});
    }

    handleIdChange(event) {
        this.setState({id: event.target.value});
    }

    handleSubmit(event){
        event.preventDefault();
        editMessage(this.state.id, this.state.title, this.state.content);
    }

    render(){
        return(
            <form onSubmit={this.handleSubmit}>
                <label>Id: </label>
                <input type="text" value={this.state.id} onChange={this.handleIdChange}/>
                <label>Title: </label>
                <input type="text" value={this.state.title} onChange={this.handleTitleChange}/>
                <label>Content: </label>
                <input type="text" value={this.state.content} onChange={this.handleContentChange}/>
                <button type="submit">Edit an existing message in the database</button>
            </form>
        )
    }
}

export default EditMessage;