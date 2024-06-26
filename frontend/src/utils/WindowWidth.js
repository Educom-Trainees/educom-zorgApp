import { computed, onMounted, onUnmounted, ref } from "vue"

/**
* calculates viewport width and returns it along with bootstrap size (type)
* @returns {Number} width
* @returns {String} type
*/
export function useBreakpoints() {
  let windowWidth = ref(window.innerWidth)

  const onWidthChange = () => windowWidth.value = window.innerWidth
  onMounted(() => window.addEventListener('resize', onWidthChange))
  onUnmounted(() => window.removeEventListener('resize', onWidthChange))
  
  const type = computed(() => {
      if (windowWidth.value < 576) return 'xs'
      if (windowWidth.value < 768) return 'sm'
      if (windowWidth.value < 992) return 'md'
      if (windowWidth.value < 1200) return 'lg'
      if (windowWidth.value < 1400) return 'xl'
      else return 'xxl'
  })

  const width = computed(() => windowWidth.value)

  return { width, type }
}