import { useEffect, useState } from "react"

// only return the specified 'value' if it hasn't been updated
// within the last 'delay' ms.
//
export function useDebounce(value: any, delay: number) {
  // state and setter for debounced value.
  const [debounceValue, setDebounceValue] = useState(value)

  useEffect(
    () => {
      // update debounced value after delay.
      const handler = setTimeout(() => {
        setDebounceValue(debounceValue)
      }, delay)

      // cancel the timeout if value changes. If the previous timer
      // hasn't finished, it'll also be cleared and restarted.
      return () => {
        clearTimeout(handler)
      }
    }, [value, delay])

  return debounceValue
}