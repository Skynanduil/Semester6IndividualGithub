import FAAS_API from "./FAAS_API";

export function getFunctionCalledCountFromFAAS(){
    return FAAS_API.get();
}