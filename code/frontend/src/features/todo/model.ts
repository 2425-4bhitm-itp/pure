import { ToDo } from "./todo"
import { Observable } from "../observable"

interface Model {
    todos: ToDo[]
}
const state: Model = {
    todos: []
}
const store = new Observable<Model>(state)
export { store }