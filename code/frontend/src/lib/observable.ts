/** small library for reactive programming
 * (c) Christian Aberger (2024)
 * @author Christian Aberger
 * https://www.aberger.at
 */

type Subscription<T> = (model: T) => void

export class Observable<T extends object> implements ProxyHandler<T> {
    private readonly subscriptions = new Set<Subscription<T>>()
    private proxy: T
  
    constructor(initialState: T) {
        this.proxy = new Proxy(initialState, this)
    }
    get(target: T, property: string | symbol, receiver: any): any {
        return Reflect.get(target, property, receiver)
    }
    set(target: T, property: string | symbol, newValue: any, receiver: any) {
        const done = Reflect.set(target, property, newValue, receiver)
        this.subscriptions.forEach(sub => {
            sub(target)
        })
        return done
    }
    subscribe(subscription: Subscription<T>): void {
        this.subscriptions.add(subscription)
        subscription(this.proxy)
    }
    get value() {
        return this.proxy
    }
}
