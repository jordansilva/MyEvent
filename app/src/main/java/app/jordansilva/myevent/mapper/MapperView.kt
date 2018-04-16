package app.jordansilva.myevent.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <D> the domain input type
 * @param <V> the view output type
 */
interface MapperView<in D, out V> {
    fun mapToView(type: D): V
}