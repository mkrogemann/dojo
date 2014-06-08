class RomanNumerals

  def self.from_integer(int)
    if int >= 4000
      return 'M' * thousands(int) + from_integer(int - thousands(int) * 1000)
    end
    int_string = int.to_s
    num_digits = int_string.length
    (1..num_digits).to_a.inject('') do |result, pos|
      digit = int_string[pos - 1].to_i
      result += convert_digit(digit, num_digits - pos + 1)
    end
  end

  private

  def self.thousands(int)
    int / 1000
  end

  def self.convert_digit(digit, magnitude)
    case digit
    when 1..3
      numeral_for_one(magnitude) * digit
    when 4
      numeral_for_one(magnitude) + numeral_for_five(magnitude)
    when 6..8
      numeral_for_five(magnitude) + numeral_for_one(magnitude) * (digit - 5)
    when 9
      numeral_for_one(magnitude) + numeral_for_one(magnitude + 1)
    when 0
      ''
    end
  end

  def self.numeral_for_one(magnitude)
    { 1 => 'I', 2 => 'X', 3 => 'C', 4 => 'M' }[magnitude]
  end

  def self.numeral_for_five(magnitude)
    { 1 => 'V', 2 => 'L', 3 => 'D' }[magnitude]
  end

end
