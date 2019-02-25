using Tech.WebAPI.Domain.ValueObjects;

namespace Tech.WebAPI.Domain.Extension
{
    public static class TagExtension
    {
        public static string ToString(this Tag[] tags)
        {
            string result = string.Empty;

            foreach (Tag t in tags)
                result += $"{t} ";

            return result;
        }
    }
}