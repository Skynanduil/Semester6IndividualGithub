import API from './API';

export function testAPI(){
    return API.get('/post/test');
}